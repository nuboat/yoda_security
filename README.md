Play Security
==================

Public Access declaration
```
LIST:
yoda.security.publiclist = ["GET /signin", "POST /enrollment", "GET /public/**"]

Graphite Host:
yoda.security.graphite.host     // null mean do not send
yoda.security.graphite.port     // port default is 2003
```


Publish Command
```
sbt publishM2

sbt publishSigned
sbt sonatypeRelease
sbt "sonatypeRelease iobeid-xxxx"

sbt sonatypeList
sbt sonatypeClose
sbt sonatypeDrop
```


------
Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 