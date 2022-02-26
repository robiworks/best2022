# Base image
FROM postgres:alpine

# Make folder to put our files in
RUN mkdir -p /usr/src/app
COPY database.txt /usr/src/app/database.txt

# Set PostgreSQL settings
# User: aquasystems
# Password: maribor
# Database: aquasystems
ENV POSTGRES_USER aquasystems
ENV POSTGRES_PASSWORD maribor
ENV POSTGRES_DB aquasystems

# Copy the database initialisation file over
COPY init.sql /docker-entrypoint-initdb.d/

# Expose port 5432 to allow backend communication
EXPOSE 5432
