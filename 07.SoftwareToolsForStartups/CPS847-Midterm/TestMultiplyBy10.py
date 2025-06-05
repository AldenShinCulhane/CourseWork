import unittest
import MultiplyBy10

class TestMultiplyBy10(unittest.TestCase):
    def runTest(self):
        testInput = 7
        self.assertEqual(MultiplyBy10.cps6054(testInput), 70, "incorrect multiplication")