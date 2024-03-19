import dicttoxml
import json
import time

with open('files/orig.json', 'r', encoding='utf-8') as original, \
        open('files/task2_res.xml', 'wb') as result:
    start_time = time.perf_counter()
    d = json.loads(''.join(original.readlines()))
    print('JSON parsing:', (time.perf_counter() - start_time) * 100)
    start_time = time.perf_counter()
    c = dicttoxml.dicttoxml(d)
    print('XML convertation:', (time.perf_counter() - start_time) * 100)
    result.write(c)
