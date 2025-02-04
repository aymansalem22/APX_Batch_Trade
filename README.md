# artica

job artica


+--------------------+     +------------------------+     +------------------------+     +------------------+
| FlatFileItemReader | --> | ItemProcessor (DTO → XML) | --> | StaxEventItemWriter (XML) | --> | output.xml file |
+--------------------+     +------------------------+     +------------------------+     +------------------+
|                         |                                  |                             |
v                         v                                  v                             v
Reads .txt file         Converts DTO into DeclarationModel    Serializes Java objects        Generates XML output
into DTO objects        (matches XML schema)                 into XML format                 following AEAT structure
