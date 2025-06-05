#!/bin/sh

function pause() {
  read -p "$*"
}

export LD_LIBRARY_PATH=/usr/lib/oracle/12.1/client64/lib

input="$1"

case "$input" in
    "1")
        sqlplus64 "ashincul/04016054@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SID=orcl)))" < ./query1.txt
        echo "Query 1 Performed"
        ;;
    "2")
        sqlplus64 "ashincul/04016054@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SID=orcl)))" < ./query2.txt
        echo "Query 2 Performed"
        ;;
    "3")
        sqlplus64 "ashincul/04016054@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SID=orcl)))" < ./query3.txt
        echo "Query 3 Performed"
        ;;
    "4")
        sqlplus64 "ashincul/04016054@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SID=orcl)))" < ./query4.txt
        echo "Query 4 Performed"
        ;;
    "5")
        sqlplus64 "ashincul/04016054@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SID=orcl)))" < ./query5.txt
        echo "Query 5 Performed"
        ;;
    "6")
        sqlplus64 "ashincul/04016054@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(Host=oracle.scs.ryerson.ca)(Port=1521))(CONNECT_DATA=(SID=orcl)))" < ./query6.txt
        echo "Query 6 Performed"
        ;;
    *)
        echo 'Invalid Input'
        ;;
esac
