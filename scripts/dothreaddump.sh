#!/bin/bash
for NUM in 1 2 3 4 5 
do
  jps | grep heap | cut -d ' ' -f1 | xargs jstack -l $1 | tee "threaddump_$NUM.tdump"
done

