# Verwende das offizielle Node.js-Image als Basis
FROM node:16 as builder

# Setze das Arbeitsverzeichnis innerhalb des Containers
WORKDIR /app

# Kopiere package.json und package-lock.json in das Arbeitsverzeichnis
COPY package*.json ./

# Installiere die Abhängigkeiten
RUN npm install

# Kopiere den Rest deines Angular-Projekts in das Arbeitsverzeichnis
COPY . .

# Führe den Build deiner Angular-Anwendung durch
RUN npm run build -- --prod

# Verwende ein Nginx-Image als Basis für das endgültige Image
FROM nginx:alpine

# Kopiere den Build aus dem ersten Container in den Nginx-Container
COPY --from=builder /app/dist /usr/share/nginx/html

# Exponiere den Port 80
EXPOSE 80

# Starte den Nginx-Server beim Ausfü
