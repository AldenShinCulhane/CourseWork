
// Alden Shin-Culhane

#include "list.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void InitializeList(List *list, int maxSize) {
    list->array = (int*) malloc(maxSize*sizeof(int));
    list->length = 0;
}

void AppendToList(List *list, int item) {
    list->length += 1;
    list->array[list->length-1] = item;
}

bool DoesItemExist(List *list, int item) {
    for (int i=0; i<list->length; i++) {
        if (list->array[i] == item) {
            return true;
        }
    }
    return false;
}

char PrefixForValue(int value) {
    if (value > 60) {
        return 'X';
    } else if (value > 45) {
        return 'U';
    } else if (value > 30) {
        return 'N';
    } else if (value > 15) {
        return 'I';
    }

    return 'L';
}

void PrintList(List *list) {
    for (int i=0; i<list->length; i++) {
        int value = list->array[i];
        printf("%c%02d ", PrefixForValue(value), value);
    }
}

