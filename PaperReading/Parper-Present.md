```mermaid
sequenceDiagram
autonumber
participant C
participant S

C ->> S: Request
Note right of S: processed by a business
S ->> C: completion message
```