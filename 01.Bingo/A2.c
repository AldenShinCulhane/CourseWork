
// Alden Shin-Culhane

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "list.h"

// Forward declarations
int ValidateArgs(int argc, char **argv);
bool ReadCard(char *fileName, int card[5][5]);
void UpdateDisplay(List *callList, int card[5][5]);
bool CheckWinCondition(List *callList, int card[5][5]);
int GenerateUniqueCallNumber();
int StringToInt(char *string);


int main(int argc, char **argv) {

    // Validate args
    int isValid = ValidateArgs(argc, argv);
    if (isValid != 0) {
        exit(isValid);
    }

    // Read card 
    int card[5][5];
    char *cardFile = argv[2];
    if (ReadCard(cardFile, card) == false) {
        fprintf(stderr, "%s has bad format\n", cardFile);
        exit(4);
    }

    // Seed the random number generator
    char *seed = argv[1];
    srand(StringToInt(seed));

    // Create a call list
    List callList;
    InitializeList(&callList, 75);

    // Loop until winning, or quit ('q') is entered
    while (true) {

        UpdateDisplay(&callList, card);
        printf("enter any non-enter key for Call (q to quit): ");

        // Read a full line from the keyboard
        char buffer[100];
        fgets(buffer, sizeof(buffer), stdin);
        int len = strlen(buffer);

        // Ignore enter
        if (len < 1) {
            continue;
        }

        // Iterate over input string
        for (int i=0; i<(len-1); i++) {

            // Check for quit character
            if (buffer[i] == 'q') {
                printf("\n >> QUIT\n\n");
                exit(0);
            }

            // Check for a win
            if (CheckWinCondition(&callList, card)) {
                printf("\n >> WINNER !!!\n\n");
                exit(0);
            }

            // Generate a new call number
            int newNum = GenerateUniqueCallNumber(&callList);
            AppendToList(&callList, newNum);
            UpdateDisplay(&callList, card);
        }
    }
}

bool RangeCheck(int card[5][5], int row) {
    return (((card[row][0] >=  0 && card[row][0] <= 15)) &&
            ((card[row][1] >= 16 && card[row][1] <= 30)) &&
            ((card[row][2] >= 31 && card[row][2] <= 45)  && (row != 2)) &&
            ((card[row][3] >= 46 && card[row][3] <= 60)) && 
            ((card[row][4] >= 61 && card[row][4] <= 75))) ||
            ((row == 2) && (card[2][2] == 0));
}

bool UniquenessCheck(int card[5][5]) {
    for (int col=0; col<5; ++col) {
        for (int row=0; row<5; ++row) {
            int val1 = card[row][col];
            for (int i=row+1; i<5; ++i) {
                int val2 = card[i][col];
                if (val1 == val2) {
                    return false;
                }
            }
        }
    }
    return true;
}

bool ReadCard(char *fileName, int card[5][5]) {

    FILE *file = fopen(fileName, "r");

    int row = 0;
    char buffer[100];
    while (fgets(buffer, sizeof(buffer), file) != 0) {

        // Check for too many lines
        if (row > 5) {
            return false;
        }

        // Null terminate the buffer
        buffer[strlen(buffer)] = '\0';

        // Check line for expected length
        if (strlen(buffer) != 15) {
            return false;
        }

        // Parse line
        int matches = sscanf(buffer, "%d %d %d %d %d", 
                        &card[row][0], &card[row][1], &card[row][2],
                        &card[row][3], &card[row][4]);

        // Check that 5 integers were matched
        if (matches != 5) {
            return false;
        }

        // Range checks
        if (RangeCheck(card, row) == false) {
            return false;
        }

        // Increment row
        ++row;
    }

    // Uniqueness check
    if (UniquenessCheck(card) == false) {
        return false;
    }

    fclose(file);
    return true;
}

bool IsMarked(List *callList, int card[5][5], int row, int col) {
    if ((row == 2) && (col == 2)) {
        return true;
    }
    return DoesItemExist(callList, card[row][col]);
}

void PrintCard(List *callList, int card[5][5]) {
    printf("\nL   I   N   U   X\n");
    for (int row=0; row<5; ++row) {
        for (int col=0; col<5; ++col) {
            char marked = IsMarked(callList, card, row, col) ? 'm' : ' ';
            printf("%02d%c ", card[row][col], marked);
        }
        printf("\n");
    }
}

void UpdateDisplay(List *callList, int card[5][5]) {
    system("clear");
    printf("CallList: ");
    PrintList(callList);
    PrintCard(callList, card);
}

bool CheckWinCondition(List *callList, int card[5][5]) {

    // Check each row
    for (int row=0; row<5; ++row) {
        bool allMarked = true;
        for (int col=0; col<5; ++col) {
            if (IsMarked(callList, card, row, col) == false) {
                allMarked = false;
                break;
            }
        }
        if (allMarked) {
            return true;
        }
    }

    // Check each column
    for (int col=0; col<5; ++col) {
        bool allMarked = true;
        for (int row=0; row<5; ++row) {
            if (IsMarked(callList, card, row, col) == false) {
                allMarked = false;
                break;
            }
        }
        if (allMarked) {
            return true;
        }
    }

    // Check the four corners
    if (IsMarked(callList, card, 0, 0) && IsMarked(callList, card, 0, 4) &&
        IsMarked(callList, card, 4, 0) && IsMarked(callList, card, 4, 4))
    {
        return true;
    }

    return false;
}

int GenerateUniqueCallNumber(List *callList) {
    int newNum;
    do {
        newNum = (rand() % 75) + 1;
    } while (DoesItemExist(callList, newNum) == true);
    return newNum;
}

int StringToInt(char *string) {
    char *end;
    long value = strtol(string, &end, 10);
    return (int)value;
}

bool IsInteger(char *string) {
    char *end;
    long value = strtol(string, &end, 10);
    return (*end == '\0');
}

bool IsReadableFile(char *filename) {
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        return false;
    }
    fclose(file);
    return true;
}

int ValidateArgs(int argc, char **argv) {

    // Check number of args
    if (argc != 3) {
        fprintf(stderr, "Usage: %s seed cardFile\n", argv[0]);
        return 1;
    }

    // Check first arg for integer
    char *seed = argv[1];
    if (IsInteger(seed) == false) {
        fprintf(stderr, "Expected integer seed, but got \"%s\"\n", seed);
        return 2;
    }

    // Check second arg for readable file
    char *cardFile = argv[2];
    if (IsReadableFile(cardFile) == false) {
        fprintf(stderr, "%s is nonexistent or unreadable\n", cardFile);
        return 3;
    }

    return 0;
}

