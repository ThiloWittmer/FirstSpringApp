#!/usr/bin python3
import os

pathData = "uebersetzungen.csv"
pathWrite = "./src/main/resources/messages"

if 'projekt' in os.listdir():
    pathData = "./projekt/uebersetzungen.csv"
    pathWrite = "./projekt/src/main/resources/messages"

with open(pathData, "r") as f:
    lines = f.readlines()
    langs = lines[0]
    langs = langs.split(";")[1:]
    defaultMessProp = open(pathWrite + ".properties", "w")
    for i in range(len(langs)):
        msg_prop = open(pathWrite + "_" + langs[i].strip('\n') + ".properties", "w")
        for line in lines[1:]:
            if  not line.startswith('#') and not line == "\n":
                line = line.split(";")
                prop = line[0] + "=" + line[i+1].strip("\n") +"\n"
                msg_prop.write(prop)
                if i == 0:
                    defaultMessProp.write(prop)
        msg_prop.close()
        defaultMessProp.close()