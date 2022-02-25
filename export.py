#!/usr/bin/python

import os
import shutil

# Get all subdirectories
dirs = os.listdir("./Arhiv_2021/")

for dir in dirs:
    src = "./Arhiv_2021/" + dir + "/WWT_VOLUMEN_17_LIT277_.TXT"
    dest = "./Export_2021/" + dir + ".txt"
    shutil.copyfile(src, dest)

print("[Export] Export finished.")
