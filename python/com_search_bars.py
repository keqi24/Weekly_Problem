#!/usr/bin/python

import sys

def input_array() :
    line = raw_input()
    array = line.split()
    intA = map(int, array)
    intA.sort(reverse=True)
    return intA


def check(sum, data):
    if sum == 0:
        return True
    index = 0
    for d in data:
        index += 1
        if sum == d:
            return True
        elif sum < d:
            continue
        else:
            if check(sum-d, data[index:]):
                return True
    return False


caseNum = int(raw_input())
while (caseNum > 0) :
    sum = int(raw_input())
    print "sum:", sum
    elementNum = int(raw_input())
    print "elementNum:", elementNum
    data = input_array()
    print "data:", data
    index = 0
    result = "No"
    for d in data:
        if check(sum, data[index:]):
            result = "Yes"
            break
        else:
            index += 1

    print result
    caseNum -= 1

