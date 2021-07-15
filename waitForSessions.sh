#!/bin/bash

curl -sSL http://$IP_ZALENIUM:4444/grid/api/sessions
while curl -sSL http://$IP_ZALENIUM:4444/grid/api/sessions | grep -q sessions > /dev/null;
do
    sleep 5
    echo "sessions still active..."
done
echo "No more selenium sessions active at http://$IP_ZALENIUM:4444"

curl -sSL http://$IP_ZALENIUM:4444/grid/api/sessions