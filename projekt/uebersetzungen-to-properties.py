#!/usr/bin python3

with open("uebersetzungen.csv", "r") as f:
    lines = f.readlines()
    langs = lines[0]
    langs = langs.split(";")[1:]
    for i in range(len(langs)):
        msg_prop = open("./src/main/resources/messages_" + langs[i].strip('\n') + ".properties", "x")
        for line in lines[1:]:
            if  not line.startswith('#') and not line == "\n":
                lineLst = line.split(";")
                prop = lineLst[0] + "=" + lineLst[i+1].strip("\n") +"\n"
                msg_prop.write(prop)