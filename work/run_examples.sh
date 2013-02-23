#!/bin/bash
find examples/ | grep "^[^.]*.js$" | xargs ./run.sh
