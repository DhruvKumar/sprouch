Sprouch - Scala library for CouchDB/BigCouch
============================================================

Sprouch is an asynchronous CouchDB client based on Spray.

Features
--------

- Scala 2.9 and 2.10 supported
- Tested with CouchDB and BigCouch/Cloudant
- Asynchronous _and_ synchronous APIs
- Connection via HTTP or HTTPS, Authorization via HTTP Basic Auth 
- CRUD operations on plain old Scala Classes
- Javascript Views
- Attachments
- Bulk actions
- more features planned: see tickets

Documentation
-------------
- Tutorials: http://sprouch.blogspot.com/
- Scaladocs: http://kimstebel.github.com/sprouch/scaladoc/2.9.2/#package
- For json serialization Sprouch uses https://github.com/spray/spray-json

Mailing list
------------

https://groups.google.com/forum/?fromgroups#!forum/sprouch

Sbt
---

### Scala 2.9 ###

```scala
resolvers += "sprouch repo" at "http://kimstebel.github.com/sprouch/repository"

libraryDependencies += "sprouch" % "sprouch_2.9.2" % "0.5.11"
```

### Scala 2.10 ###

```scala
resolvers += "sprouch repo" at "http://kimstebel.github.com/sprouch/repository"

libraryDependencies += "sprouch" % "sprouch_2.10" % "0.5.11"
```

Contribute!
-----------

Contributions are always welcome! Please drop me a line at kim.stebel@gmail.com if you want to help.

[![githalytics.com alpha](https://cruel-carlota.pagodabox.com/ffb2ae45f4a1c925fe8fc378a16c6708 "githalytics.com")](http://githalytics.com/KimStebel/sprouch)
