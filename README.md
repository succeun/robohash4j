RoboHash
========

The source code for [RoboHash.org](https://robohash.org/).

It basically copy/pastes various robot pictures together, using bits
from the SHA hash. It's not perfect, and not entirely secure, but it
gives a good gut-check to "Hey, this SHA is wrong."

Usage
-----

```java

    import com.hihds.robohash4j.Robohash;
    
    String hash = "whatever-hash-you-want"
    byte[] pngImage = Robohash.assemble(ImageSet.ROBOHASH_SET1, hash.getBytes(), Format.PNG);
    Files.write(Paths.get("file.png"), pngImage);
```

Robosets
--------

RoboHash comes with three image sets, named "ROBOHASH_SET1", "ROBOHASH_SET2", and "ROBOHASH_SET3".
Specify which set you want in the ``assemble()`` method. Alternatively,
specify the string "ROBOHASH_ANY", and RoboHash will pick an image set for you,
based on the provided hash.

The "ROBOHASH_SET1" artwork was created by Zikri Kader. The ROBOHASH_SET2" artwork was
created by Hrvoje Novakovic. The "ROBOHASH_SET3" artwork was created by Julian
Peter Arias.
The Cats/"ROBOHASH_SET4" were created by David Revoy, used under CC-BY-4.0
https://www.peppercarrot.com/en/article391/cat-avatar-generator


License
-------

The Python Code is available under the MIT/Expat license. See the
``LICENSE.txt`` file for the full text of this license. Copyright (c)
2011, Colin Davis.

The RoboHash images are available under the CC-BY-3.0 license.

Disclaimer
----------

OK, I'll admit I'm a crappy programmer. Compounding this, I wrote this
code initially to be internal-only. It's ugly, and could be a LOT nicer.

Sorry about that.

.. [RoboHash.org](https://robohash.org/)

