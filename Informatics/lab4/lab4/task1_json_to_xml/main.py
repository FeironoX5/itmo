from xml_converter import xmlConverter
from json_parser import jsonParser

with open('../files/orig.json', 'r', encoding='utf-8') as original, \
        open('../files/task1_res.xml', 'w', encoding='utf-8') as result:
    result.write(xmlConverter(jsonParser(''.join(original.readlines())).root).result)
