# Build Stage für Angular Apps
FROM node:20-alpine AS build

WORKDIR /app

# Installiere global Angular CLI
RUN npm install -g @angular/cli@15

# Baue alle vier Frontends
# 1. Kopiere und baue dbmanager_frontend
COPY dbmanager_fronted/package.json dbmanager_fronted/package-lock.json ./dbmanager_fronted/
WORKDIR /app/dbmanager_fronted
RUN npm install
COPY dbmanager_fronted /app/dbmanager_fronted
RUN ng build --configuration production --base-href /dbmanager/

# 2. Kopiere und baue frontend
COPY frontend/package.json ../frontend/package-lock.json /app/frontend/
WORKDIR /app/frontend
RUN npm install
COPY frontend /app/frontend
RUN ng build --configuration production --base-href /frontend/

# 3. Kopiere und baue game-frontend
COPY game-frontend/package.json ../game-frontend/package-lock.json /app/game-frontend/
WORKDIR /app/game-frontend
RUN npm install
COPY game-frontend /app/game-frontend
RUN ng build --configuration production --base-href /game/

# 4. Kopiere und baue mameie-frontend
COPY mameie-frontend/package.json ../mameie-frontend/package-lock.json /app/mameie-frontend/
WORKDIR /app/mameie-frontend
RUN npm install
COPY mameie-frontend /app/mameie-frontend
RUN ng build --configuration production --base-href /mameie/

# Produktionsstage - NGINX Server
FROM nginx:1.23-alpine

# Kopiere die gebauten Angular Apps nach NGINX
COPY --from=build /app/dbmanager_fronted/dist/dbmanager_fronted /usr/share/nginx/html/dbmanager
COPY --from=build /app/frontend/dist/frontend /usr/share/nginx/html/frontend
COPY --from=build /app/game-frontend/dist/game-frontend /usr/share/nginx/html/game
COPY --from=build /app/mameie-frontend/dist/mameie-frontend /usr/share/nginx/html/mameie

# Ersetze die Standard NGINX Konfiguration
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
