### Install Cobol
```
sudo apt install gnucobol
```
### Make sure the installation is success
```
cobc --version
```
### Test a simple program to compile
```
IDENTIFICATION DIVISION.
PROGRAM-ID. HELLO-WORLD.
PROCEDURE DIVISION.
    DISPLAY 'Hello world!'.
    STOP RUN.
```
### let's compile
save this as ```helloworld.cbl```

We can now compile it with ```cobc -free -x -o helloworld helloworld.cbl```

if no errors foud test with ```./helloworld```

```Hello World!```
It works.
