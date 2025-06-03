[![Better Stack Badge](https://uptime.betterstack.com/status-badges/v3/monitor/1u3in.svg)](https://uptime.betterstack.com/?utm_source=status_badge)

```mermaid
graph TD
    subgraph AI Gallery
        style AI Gallery fill:#fff5e6,stroke:#ff9933,stroke-width:2px
        A[Web Frontend<br>HTML5 UP Template] -->|User Interaction| B[User Input<br>Text Prompt]
        B -->|Text Prompt| C[API Call<br>Pollinations.AI or Similar]
        C -->|API Response| D[AI Art Generation<br>FLUX]
        D -->|Generated Image| E[Image Output<br>Gallery Display]
        A --> F[Render Hosting<br>aigallery-cloud02]
        F --> G[Image Storage<br>On cloudinary]
        E --> G
    end
    classDef frontend fill:#ff9933,stroke:#333,stroke-width:2px,color:#0f0014,rx:10,ry:10,stroke-width:3px
    classDef ai fill:#33cc99,stroke:#333,stroke-width:2px,color:#0f0014,rx:10,ry:10,stroke-width:3px
    classDef infra fill:#cccccc,stroke:#333,stroke-width:1px,rx:10,ry:10,stroke-width:3px
    classDef edgeHighlight stroke:#ff4d4d,stroke-width:3px
    class A frontend,stroke:#110117
    class D ai
    class B,C,E,F,G infra,
    linkStyle 1,2 stroke:#ff4d4d
