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
sbt publishLocal

sbt publishSigned
//sbt sonatypeBundleRelease
sbt sonatypeRelease
sbt "sonatypeRelease innorbor-1064"

sbt sonatypeList
sbt sonatypeClose
sbt sonatypeDrop
```


------
Copyright (c) 2020. Peerapat Asoktummarungsri <https://www.linkedin.com/in/peerapat>
 