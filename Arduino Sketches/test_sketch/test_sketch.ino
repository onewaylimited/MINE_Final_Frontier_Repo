#include <Ethernet.h>
#include <SPI.h>

#define SOC '<'
#define EOC '>'
#define SOR '['
#define EOR ']'

#define START_ERROR 1


byte mac[] = {0x80, 0xFD, 0xC4, 0x95, 0x34, 0xE8};  // Mac Address of Arduino
IPAddress ip(192, 168, 1, 177);  // IP Address
IPAddress myDns(192, 168, 1, 1);  // DNS
IPAddress gateway(192, 168, 1, 1);  // Gateway
IPAddress subnet(255, 255, 0, 0);  // Subnet

boolean printed = false;

EthernetServer server = EthernetServer(23); // Initiailize server on port 23

int dataArr[8];

unsigned long lastSent = 0;

void setup() {
  Serial.begin(9600);
  pinMode(LED_BUILTIN, OUTPUT);  // Set built in led to be output pin

  bitParse(bitParseTest());

  // Attempt to set up Ethernet connection
  if ((Ethernet.begin(mac)) == 0) {
    Serial.print("Failed to Configure ethernet using DHCP!\n");
    // Enter failure state forever
    while (true) {
      Serial.print("Ethernet failed to configure, waiting...\n");
      digitalWrite(LED_BUILTIN, HIGH);
      delay(500);
      digitalWrite(LED_BUILTIN, LOW);
      delay(300);
    }
  }



  // Begin listening for clients
  server.begin();
}

void loop() {
  boolean valid = true;
  if (!printed) {
    Serial.println("Server Running");
    Serial.println(Ethernet.localIP());
    printed = true;
  }
  if((millis() - lastSent) > 3000){
     sendPing();
     lastSent = millis();
  }
  char buffer[100];
  EthernetClient client = server.available();
  if (client) {
    Serial.println("Client Connected!\n");
    client.flush();
    // Read bytes from the incoming client and write them
    // to any clients connected on the server
    char c;
    int pos = 0;
    while ((c = client.read()) > -1) {
      buffer[pos] = c;
      pos ++;
    }
    printArray(buffer, pos);
    writeArrayToClient(buffer);
    delay(10);
  }
}

void printArray(char array[], int len) {
  int i;
  for (i = 0; i < len; i ++) {
    Serial.print(array[i]);
  }
}

void writeArrayToClient(char array[]) {
  int i = 0;
  while(true){
    if(array[i] == '\r' || array[i] == '\n'){
      server.write(array[i]);
      break;
    }
    else{
      server.write(array[i]);
    }
    i++;
  }
    
}

void sendPing(){
  char arr[] = {'P', 'i', 'n', 'g', '\n'};
  writeArrayToClient(arr);
}

/**
   Parse incoming command
*/
void commandParse(EthernetClient client) {
  char buffer[100];
  if (client) {
    while (true) {
      byte b = client.read();
    }
  }
}

/**
   Parse incoming request
*/
void requestParse(EthernetClient client) {

}

/**
   Parse a byte into something easier to use
   (In this case a length 8 integer array)
   We use a global variable since why not right?
*/
void bitParse(byte data) {
  for (int i = 0; i < 8; i ++) {
    dataArr[i] = bitRead(data, i);
  }
}

byte bitParseTest() {
  byte testByte = 0;
  testByte = testByte | 0b10101010;
  return testByte;
}

/**
   Used to print errors from arudino
*/
void error(int error) {
  if (error == START_ERROR) {
    char errorArr[] = "SOF error, send using correct SOF\n";
    server.write(errorArr, sizeof(errorArr));
  }
}

/**
   Call this about once a second to renew DHCP credentials
*/
void maintain() {
  int val = Ethernet.maintain();
  if (val == 0) {
    Serial.println("{maintain()}: Nothing Happend");
  }
  else if (val == 1) {
    Serial.println("{maintain()}: Renew Failure");
  }
  else if (val == 2) {
    Serial.println("{maintain()}: Renew Success");
  }
  else if (val == 3) {
    Serial.println("{maintain()}: Rebind Fail");
  }
  else if (val == 4) {
    Serial.println("{maintain()}: Rebind Success");
  }
}

