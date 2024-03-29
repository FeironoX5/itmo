import time

# Array
# data
# init(s)
# .for
# ..when closed -> add X to {data}; if next , continue
class jsonParser:
    def __init__(self, jsonData):
        start_time = time.perf_counter()
        self.root = self.jsonObject(jsonData[1:-1]).data
        print('JSON parsing:', (time.perf_counter() - start_time) * 100)

    class jsonArray:
        def __init__(self, s):
            self.data = []
            while s:
                

        def getFirstElement(self, s):
            getting_string = False
            getting_number = False
            obj = None
            stack = []
            n, i = len(s), 0
            while i < n:
                c = s[i]
                if (c == ' ' or c == '\n') and not getting_string:
                    i += 1
                    continue
                if c == '"' \
                        and not stack \
                        and not getting_string:
                    getting_string = True
                    obj = ''
                elif c in ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'] \
                        and not stack \
                        and not getting_number:
                    getting_number = True
                    obj = c
                elif getting_string:
                    if c == '"':
                        break
                    else:
                        obj += c
                elif getting_number:
                    if i == n - 1 or c == ',':
                        if '.' in obj:
                            obj = float(obj)
                        else:
                            obj = int(obj)
                        break
                    else:
                        obj += c
                else:
                    if c == '{' or c == '[':
                        stack.append([c, i])
                    elif not stack:
                        if i == n - 1 or s[i + 1] == ',':
                            break
                        else:
                            raise Exception("Wrong argument while parsing")

                    elif c == '}' or c == ']':
                        top = stack.pop()
                        if top[0] == '{' and c == '}':
                            obj = jsonParser.jsonObject(s[top[1] + 1:i]).data
                            if not stack:
                                break
                        elif top[0] == '[' and c == ']':
                            obj = jsonParser.jsonArray(s[top[1] + 1:i]).data
                            if not stack:
                                break
                        else:
                            raise Exception("Wrong argument while parsing")
                i += 1

            if obj:
                self.data.append(obj)
                return s[i if i == n - 1 else i + 2:]

    class jsonObject:
        def __init__(self, s):
            self.data = {}
            while s:
                s = self.getFirstPair(s)

        def getFirstPair(self, s):
            getting_key = False
            getting_string = False
            getting_number = False
            key = ''
            obj = None
            stack = []
            n, i = len(s), 0
            while i < n:
                c = s[i]
                if (c == ' ' or c == '\n') and not getting_string:
                    i += 1
                    continue
                if getting_key:
                    if c == '"':
                        if s[i + 1] == ':':
                            i += 1
                            getting_key = False
                        else:
                            raise Exception("Wrong argument while parsing")

                    else:
                        key += c
                elif c == '"' and key == '':
                    getting_key = True
                else:
                    if c == '"' \
                            and not stack \
                            and not getting_string \
                            and not getting_number:
                        getting_string = True
                        obj = ''
                    elif c in ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9'] \
                            and not stack \
                            and not getting_number \
                            and not getting_string:
                        getting_number = True
                        obj = ''
                    elif getting_string:
                        if c == '"':
                            break
                        else:
                            obj += c
                    elif getting_number:
                        if i == n - 1 or c == ',':
                            if '.' in obj:
                                obj = float(obj)
                            else:
                                obj = int(obj)
                            break
                        else:
                            obj += c
                    else:
                        if c == '{' or c == '[':
                            stack.append([c, i])
                        elif not stack:
                            if i == n - 1 or s[i + 1] == ',':
                                break
                            else:
                                raise Exception("Wrong argument while parsing")

                        elif c == '}' or c == ']':
                            top = stack.pop()
                            if top[0] == '{' and c == '}':
                                obj = jsonParser.jsonObject(s[top[1] + 1:i]).data
                                if not stack:
                                    break
                            elif top[0] == '[' and c == ']':
                                obj = jsonParser.jsonArray(s[top[1] + 1:i]).data
                                if not stack:
                                    break
                            else:
                                raise Exception("Wrong argument while parsing")
                i += 1
            if key != '' and obj:
                if obj == 'null':
                    obj = None
                if obj == 'true':
                    obj = True
                if obj == 'false':
                    obj = False
                self.data[key] = obj
                return s[i if i == n - 1 else i + 2:]
