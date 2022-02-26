# Base image
FROM node:alpine

# Folder to put our app in
RUN mkdir -p /usr/src/app/backend

# Set working directory so that all subsequent commands run in this folder
WORKDIR /usr/src/app/backend

# Copy package.json, package-lock.json and install dependencies
COPY package*.json ./
RUN npm ci

# Copy our app
COPY . .

# Expose port to access server
EXPOSE 8080

# Command to run our app
CMD node index.js