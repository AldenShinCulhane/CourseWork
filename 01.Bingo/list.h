
// Alden Shin-Culhane

#include <stdbool.h>

typedef struct {
    int *array;
    int length;
} List;

void InitializeList(List *list, int maxSize);
void AppendToList(List *list, int item);
bool DoesItemExist(List *list, int item);
void PrintList(List *list);

