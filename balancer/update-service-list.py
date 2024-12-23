import json
import requests
import subprocess
from time import sleep

consul_url = "http://service-discovery:8500/v1/health/service/labworks-rest"

cfg_input_template = "\tserver {} {}:{} ssl verify none\n"
haproxy_dir = "/usr/local/etc/haproxy"
haproxy_default_cfg = haproxy_dir + "/haproxy.cfg"
haproxy_last_daemon_pid_file = haproxy_dir + "/last-daemon-pid.txt"

read_first_lines_cnt = 48

last_instances = []


def read_status() -> list[dict[str, str]]:
    resp = requests.get(consul_url)
    body = resp.content
    status = json.loads(body)

    instances = []
    for instance in status:
        instances.append({
            "ID": instance["Service"]["ID"],
            "Address": instance["Service"]["Address"],
            "Port": instance["Service"]["Port"]})

    return instances


def build_config(instances: list[dict[str, str]]) -> None:
    with open(haproxy_default_cfg) as file:
        cfg = file.readlines()[:read_first_lines_cnt]

    for instance in instances:
        cfg.append(cfg_input_template.format(instance["ID"], instance["Address"], instance["Port"]))

    with open(f"{haproxy_dir}/haproxy_new_.cfg", "w") as file:
        file.writelines(cfg)


def read_last_daemon_pid() -> int:
    with open(haproxy_last_daemon_pid_file) as file:
        return int(file.readline())


def reload_config() -> None:
    subprocess.run(["sh", "-c",
                    f"haproxy -f {haproxy_dir}/haproxy_new_.cfg -D -p {haproxy_last_daemon_pid_file} -sf {read_last_daemon_pid()}"])


def main():
    global last_instances

    while True:
        instances = read_status()

        if instances != last_instances:
            last_instances = instances
            build_config(instances)
            reload_config()

        sleep(2)


if __name__ == "__main__":
    main()
