# artica

job artica

## Requirements
must use xstream library to convert the input to the output format

## description

1- input in itemreader.. read file --> src/test/resources/trade.txt
2- itemprocessor.. process the input and convert it to the output format
3- output in itemwriter.. write file --> datas/trade/output/declaration.xml
4- the output file should be in xml format and similar to the reference --> datas/trade/output/declaracion_trade_2025_standard.xml file
5- package DTO that resides in the package com.bbva.fsia.dto.artica
6- the job package com.bbva.fsia.batch contain three steps processtrade.java,readtrade.java,writedeclarationmodel172.java.
=======

![digram](https://github.com/user-attachments/assets/43f9669a-766a-496a-847c-20dfa3483808)

