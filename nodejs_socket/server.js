const net = require('node:net');
const { randomUUID } = require('crypto');
const cookie = require("cookie");

const cookieValue = cookie.serialize(
    "authToken", "your-secure-token-value", {
    httpOnly: true,                 // Prevents client-side JS read access
    secure: process.env.NODE_ENV === "production", // Require HTTPS in prod
    sameSite: "lax",                // Protects against CSRF
    maxAge: 60 * 60 * 24,           // 1 day expiration (in seconds)
    path: "/"                       // Available across the whole domain
  });

const server = net.createServer((socket) => {
    console.log('Client connected.');
    const message = "hello client, this is server";
    const id = randomUUID();
    console.log(id);
    // Handle incoming data from the client
    socket.on('data', (data) => {
        console.log(data.buffer);
        const headers = data.toString().trim();
        console.log(`Received from client: `+ headers);
        if(headers.toLowerCase().includes('http')){
            const length = Buffer.byteLength(message);
            console.info("--------HTTP Connection----------------");
            console.info("-------send http response---------------")
            socket.write('HTTP/1.1 200 OK\r\n');
            socket.write('Content-Type: text/html; charset=UTF-8\r\n');
            socket.write('Content-Length: '+length+'\r\n');
            socket.write('Set-Cookie: token '+cookieValue+'\r\n');
            socket.write('Connection: close\r\n');
            socket.write('\r\n');
            socket.write(message);
            socket.end();
            console.info("---------http respond sent---------------");
        }else{
            console.info("---------------TCP Connection---------------");
            socket.write('hello client, this is server\r\n');
            socket.end();
        }
    });

    // Handle client disconnection
    socket.on('end', () => {
        console.log('Client disconnected.');
    });

    // Handle connection errors
    socket.on('error', (err) => {
        console.error(`Socket error: ${err.message}`);
    });
});

// Start listening on port 8080
server.listen(8080, '127.0.0.1', () => {
    console.log('TCP Server running on 127.0.0.1:8080');
});
