// Your profile name of the sonatype account. The default is the same with the organization value
sonatypeProfileName := "in.norbor"

// To sync with Maven central, you need to supply the following information:
publishMavenStyle := true

//publishTo := sonatypePublishToBundle.value

// License of your choice
licenses := Seq("MIT" -> url("https://raw.githubusercontent.com/nuboat/play-security/master/LICENSE"))
homepage := Some(url("https://github.com/nuboat/yoda-security"))
scmInfo := Some(
  ScmInfo(
    url("https://github.com/nuboat/play-security"),
    "scm:git@github.com:nuboat/yoda-security.git"
  )
)
developers := List(
  Developer(id = "nuboat"
    , name = "Peerapat Asoktummarungsri"
    , email = "nuboat@gmail.com"
    , url = url("https://github.com/nuboat"))
)
