#!/usr/bin/python

import os

# Get all files
files = os.listdir("./Export_2021/")

# Output file
output = open("./Merge_2021/Database.txt", "w")

for f in files:
    file = open("./Export_2021/" + f)
    output.write(file.read())
