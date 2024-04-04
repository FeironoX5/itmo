import time


class xmlConverter:
    def __init__(self, data):
        start_time = time.perf_counter()
        self.result = self.process(data)
        print('XML convertation:', (time.perf_counter() - start_time) * 100)

    def process(self, d, padding=""):
        if type(d) is dict:
            return self.processObject(d, padding)
        elif type(d) is list:
            return self.processArray(d, padding)
        else:
            return "%s%s" % (padding, d)

    def processObject(self, d, padding=""):
        res = []
        for k in d:
            v = d[k]
            res.append("%s<%s>" % (padding, k))
            res.append(self.process(v, "\t" + padding))
            res.append("%s</%s>" % (padding, k))
        return "\n".join(res)

    def processArray(self, d, padding=""):
        res = []
        for v in d:
            res.append(self.process(v, padding))
        return "\n".join(res)
