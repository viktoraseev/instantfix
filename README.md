instantfix
==========

Micro library for parsing fix messages as fast as possible, retrieving only needed information.

Benchmark test(Intel core i5 2.5 GHz):
* ByteFixMessage Msg/s 1561128.526646
* GenericFixMessage  Msg/s 970003.895598
* TupleFixMessage Msg/s 598509.734797
