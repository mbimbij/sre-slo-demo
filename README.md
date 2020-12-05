:fr: Sommaire / :gb: Table of Contents
=================

* [Documentation en Français](#fr-poc--demo-slo)
* [English Documentation](#gb-poc--demo-slo)

---

# :fr: poc / demo SLO

Le but de ce projet est de mettre les mains dans le cambouis et de s'essayer à implémenter des concepts de SRE:

SLI, SLO, et tout un monitoring qui permet de le mesurer et de le visualiser, tout cela sur une API REST Spring "hello-world" 
avec prometheus et grafana branché dessus.

L'application expose automatiquement, pour tous les endpoints REST: 

- le nombre total de requêtes sur chaque endpoint 
  - prometheus: `http_server_requests_seconds_count`
- la latence cumulée sur chaque endpoint 
  - prometheus: `http_server_requests_seconds_sum` 
- la latence max sur chaque endpoint
  - prometheus: `http_server_requests_seconds_max`
- les pourcentiles de latence sur chaque endpoint
  - application.properties: `management.metrics.distribution.percentiles.http`
  - prometheus: `http_server_requests_seconds_max`

Ces statistiques sont calculées, côté appli, pour une fenêtre de temps définies dans la propriété: 
``

# :gb: poc / demo SLO

(coming soon)