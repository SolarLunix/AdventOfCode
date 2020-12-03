import numpy as np


def read_passwords(a_file):
    with open(a_file) as f:
        return [x for x in f]


def check_passwords_1(passwords):
    unique_passwords = 0
    for password_and_rule in passwords:
        values, character, password = password_and_rule.split(" ")
        character = character[0]
        minimum, maximum = values.split("-")
        minimum = int(minimum)
        maximum = int(maximum)

        u_values, count = np.unique(passwords, return_counts=True)
        values = dict(zip(u_values, count))


file = "C:\\Users\\Solar\\IdeaProjects\\AdventOfCode\\2020\\src\\ExternalInformation\\Passwords.txt"
passwords_list = ["1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc"]
check_passwords_1(passwords_list)

