
https://github.com/ory/hydra

https://www.ory.sh/docs/hydra/5min-tutorial



docker pull oryd/hydra:v2.1.2
docker pull postgres:11.8

docker compose -f quickstart.yml \
    -f quickstart-postgres.yml \
    up



client=$(hydra create client \
    --endpoint http://127.0.0.1:4445/ \
    --format json \
    --grant-type client_credentials)

client_id=$(echo $client | jq -r '.client_id')
client_secret=$(echo $client | jq -r '.client_secret')    


hydra perform client-credentials \
  --endpoint http://127.0.0.1:4444/ \
  --client-id $client_id \
  --client-secret $client_secret

export client_token="..."

hydra introspect token \
  --format json-pretty \
  --endpoint http://127.0.0.1:4445/ \
  $client_token
  
hydra ls oauth2-clients \
  --endpoint http://127.0.0.1:4445/


