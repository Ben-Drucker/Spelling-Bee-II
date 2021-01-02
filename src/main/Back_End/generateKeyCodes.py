def main():
    fp = open("CopyPaste", "w")
    for charCode in range(65, 91):
        fp.write("case KeyCode."+chr(charCode)+"\n")
    fp.close()

main()
