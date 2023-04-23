FROM node:alpine
WORKDIR /app
COPY package.json ./
COPY package-lock.json ./ 
COPY ./ ./ 

RUN apk add --no-cache bash

RUN chmod +x ./enc.sh

CMD ["npm", "start"]