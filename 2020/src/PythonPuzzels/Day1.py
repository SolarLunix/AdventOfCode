import numpy as np


def find_multi_sum_2(expenses, desired_sum):
    expenses_a = np.array(expenses)
    expenses_b = np.expand_dims(expenses_a, axis=-1)

    dim_a = expenses_a.shape[0]

    expenses_added = np.zeros((dim_a, dim_a))

    expenses_added += expenses_a
    expenses_added += expenses_b

    index = np.where(expenses_added == desired_sum)
    for i in index:
        unique = np.unique([expenses[i[0]], expenses[i[1]]])
        if len(unique) == 2:
            print("{0} and {1} add up to {2}".format(expenses[i[0]], expenses[i[1]], desired_sum))
            print("Together they multiply to {0}\n".format((expenses[i[0]] * expenses[i[1]])))


def find_multi_sum_3(expenses, desired_sum):
    expenses_a = np.array(expenses)
    expenses_b = np.expand_dims(expenses_a, axis=-1)
    expenses_c = np.expand_dims(expenses_b, axis=-1)

    dim_a = expenses_a.shape[0]

    expenses_added = np.zeros((dim_a, dim_a, dim_a))

    expenses_added += expenses_a
    expenses_added += expenses_b
    expenses_added += expenses_c

    index = np.where(expenses_added == desired_sum)
    for i in index:
        unique = np.unique([expenses[i[0]], expenses[i[1]], expenses[i[2]]])
        if len(unique) == 3:
            print("{0}, {1}, and {2} add up to {3}".format(expenses[i[0]], expenses[i[1]], expenses[i[2]], desired_sum))
            print("Together they multiply to {0}\n".format((expenses[i[0]] * expenses[i[1]] * expenses[i[2]])))


def read_expenses(a_file):
    with open(a_file) as f:
        return [int(x) for x in f]


file = "C:\\Users\\Solar\\IdeaProjects\\AdventOfCode\\2020\\src\\ExternalInformation\\ExpenseReport.txt"
DESIRED_SUM = 2020
find_multi_sum_2([1721, 979, 366, 299, 675, 1456], DESIRED_SUM)
find_multi_sum_3([1721, 979, 366, 299, 675, 1456], DESIRED_SUM)

print("\n=============================================\n")

find_multi_sum_2(read_expenses(file), DESIRED_SUM)
find_multi_sum_3(read_expenses(file), DESIRED_SUM)

