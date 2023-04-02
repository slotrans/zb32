import sys
import random
import argparse


ALPHABET = "ybndrfg8ejkmcpqxot1uwisza345h769"


def generate_zb32_string(length):
    return "".join([ALPHABET[random.randrange(0, 32)] for _ in range(length)])


def main(args) -> int:
    if args.seed:
        random.seed(args.seed)

    for _ in range(args.count):
        print(generate_zb32_string(args.length))

    return 0


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Z-Base-32 random string generator")
    parser.add_argument("-s", "--seed", type=int, help="Use a specific random seed")
    parser.add_argument("-l", "--length", type=int, default=16, help="Length of each generated string")
    parser.add_argument("-c", "--count", type=int, default=1, help="Count of strings to generate")
    args = parser.parse_args()

    sys.exit(main(args))
