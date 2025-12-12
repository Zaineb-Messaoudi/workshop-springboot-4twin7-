## Spring Boot Workshop

## HTTP Status Codes & Most Common Spring Exceptions

| HTTP Status                  | Main Spring Exception(s)                                                                 | Why it happens                                                                                           | Recommended Fix / Best Practice                                                                                                  |
|------------------------------|------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------|
| **400 Bad Request**          | `MethodArgumentNotValidException`<br>`HttpMessageNotReadableException`<br>`ConstraintViolationException`<br>`BindException` | Invalid/malformed payload, missing required fields, wrong types, JSON syntax error                       | • Always use `@Valid @RequestBody`<br>• Bean Validation annotations (`@NotNull`, `@Size`, `@Positive`, etc.)<br>• Global exception handler returning `FieldError` list |
| **401 Unauthorized**         | `AuthenticationException`<br>`BadCredentialsException`<br>`InvalidBearerTokenException` | No credentials, wrong password, expired/invalid JWT                                                             | • Check username/password<br>• Refresh token flow<br>• Custom `AuthenticationEntryPoint` that returns clean JSON |
| **403 Forbidden**            | `AccessDeniedException`                                                                  | User authenticated but lacks required role/authority                                                            | • `@PreAuthorize("hasRole('ADMIN')")` or `@Secured`<br>• Method-level security<br>• Custom `AccessDeniedHandler` |
| **404 Not Found**            | `NoHandlerFoundException`<br>`ResourceNotFoundException` (custom)<br>`EmptyResultDataAccessException` | Wrong URL or entity not found                                                                                   | • Throw custom `ResourceNotFoundException` in service<br>• Global handler → 404 with clear message |
| **405 Method Not Allowed**   | `HttpRequestMethodNotSupportedException`                                                 | Using POST on a GET-only endpoint, etc.                                                                          | • Correct `@GetMapping`, `@PostMapping`, …<br>• Allow needed methods explicitly |
| **406 Not Acceptable**       | `HttpMediaTypeNotAcceptableException`                                                    | Client `Accept` header asks for unsupported format (e.g. XML when only JSON is produced)                    | • Define `produces = {"application/json", "application/xml"}`<br>• Or negotiate properly |
| **409 Conflict**             | `DataIntegrityViolationException`<br>`DuplicateKeyException`<br>`OptimisticLockingFailureException` | Duplicate unique key, concurrent update conflict                                                                | • Check existence before insert<br>• Use `@Version` + retry logic<br>• Return meaningful message |
| **415 Unsupported Media Type**| `HttpMediaTypeNotSupportedException`                                                    | Wrong `Content-Type` (e.g. `text/plain` instead of `application/json`)                                          | • Always send `Content-Type: application/json`<br>• Declare `consumes = "application/json"` |
| **422 Unprocessable Entity** | Custom validation or `MethodArgumentNotValidException` (you map it to 422)               | Payload is valid JSON but fails business rules                                                                   | • Custom validators<br>• Return 422 instead of 400 when semantics are wrong |
| **426 Upgrade Required**     | Rarely used in APIs                                                                      | Client uses outdated API version                                                                                 | • Version your API (`/api/v1/`, `/api/v2/`)<br>• Return `Upgrade` header if needed |
| **429 Too Many Requests**    | `RateLimitExceededException` (custom with Bucket4j/Resilience4j)                         | Client exceeded rate limit                                                                                       | • Implement rate limiting (Bucket4j, Redis, etc.)<br>• Return `Retry-After` header |
| **500 Internal Server Error**| Any unhandled exception (`NullPointerException`, runtime bugs, etc.)                    | Bug or unexpected state on server side                                                                           | • NEVER expose stacktrace in prod<br>• Global `@ControllerAdvice`<br>• Log with correlation ID |
| **502 Bad Gateway**          | Proxy / API Gateway error                                                                | Upstream service returned invalid response                                                                       | • Check Nginx, Kong, Zuul, Spring Cloud Gateway config |
| **503 Service Unavailable**  | Overload, maintenance, DB down                                                           | Server temporarily unable to handle request                                                                      | • Health checks + circuit breaker<br>• Return `Retry-After` |
| **504 Gateway Timeout**      | Timeout calling another microservice                                                     | Downstream service too slow or unreachable                                                                       | • Set proper timeouts (WebClient, Feign, RestTemplate)<br>• Fallbacks + circuit breaker |

## Most Common Spring / JPA Exceptions & How to Handle Them

| Exception                                        | Typical Cause                                                   | Recommended Handling                                                                                            |
|--------------------------------------------------|-----------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| `MethodArgumentNotValidException`                | `@Valid` failed on `@RequestBody`                               | `@ExceptionHandler` → extract `fieldErrors` → return 400 with details                                           |
| `ConstraintViolationException`                   | Validation on `@PathVariable` / `@RequestParam`                 | Same global handler or specific one for 400                                                                     |
| `HttpMessageNotReadableException`                | Malformed JSON, missing body, wrong date format                 | Clear message: "Malformed JSON", "Required request body is missing"                                            |
| `DataIntegrityViolationException`                | Unique constraint, FK violation                                 | Translate to 409 Conflict with user-friendly message                                                           |
| `DuplicateKeyException` (PostgreSQL/MySQL)       | Insert duplicate primary/unique key                             | Same as above – 409                                                                                             |
| `OptimisticLockingFailureException`              | `@Version` conflict                                             | Return 409 + "Resource was modified by another user – please reload"                                           |
| `EntityNotFoundException` / `EmptyResultDataAccessException` | `repository.getReferenceById(id)` or `findById` on missing entity | Throw custom `ResourceNotFoundException` → 404                                                                  |
| `JpaSystemException` / `TransactionSystemException` | Transaction rollback issues (validation inside @Transactional) | Usually caused by validation exception – catch and rethrow as business exception                               |
| `AccessDeniedException`                          | Spring Security denies access                                   | Custom `AccessDeniedHandler` returning JSON 403 instead of redirect                                            |
| `MethodNotAllowedException`                      | Wrong HTTP verb                                                 | Already handled by Spring (405)                                                                                 |
| `MissingServletRequestParameterException`        | Required `@RequestParam` missing                                | Global handler → 400                                                                                            |
| `TypeMismatchException` / `ConversionFailedException` | Sending "abc" where Integer expected                     | 400 with clear message                                                                                          |
| `AsyncRequestTimeoutException`                   | `@Async` method timeout                                         | Configure async timeout or handle gracefully                                                                    |
| `MaxUploadSizeExceededException`                 | File too big (multipart)                                        | Global handler → 413 Payload Too Large + clear message                                                         |
| `AuthenticationServiceException`                 | Problem inside your AuthenticationProvider                      | Usually 500 – log and investigate                                                                              |
| `LockedException`                                | Account locked (too many failed logins)                         | Return 423 Locked or 401 with specific message                                                                  |


# Spring Scheduler & Spring AOP – README Complet en Français  
# Spring Scheduler + Spring AOP – Guide Complet & Bonnes Pratiques (en français)
README indispensable pour tout projet Spring Boot qui utilise des tâches planifiées ou de l’AOP.

## 1. Spring Scheduler (@Scheduled)

### Activer le scheduling (une seule fois dans l’application)
@SpringBootApplication
@EnableScheduling                   // Obligatoire !
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

### Exemples classiques

@Component
@Slf4j
public class TachesPlanifiees {

    // Toutes les 5 secondes après la fin de la tâche précédente
    @Scheduled(fixedDelay = 5000)
    // @Scheduled(fixedDelayString = "${app.delay}") // depuis application.yml
    public void toutesLes5Secondes() {
        log.info("Tâche fixedDelay exécutée à {}", LocalDateTime.now());
    }

    // Toutes les 10 secondes, même si la précédente n’est pas terminée (risque de chevauchement)
    @Scheduled(fixedRate = 10000)
    public void toutesLes10SecondesFixedRate() {
        log.info("Tâche fixedRate – attention au chevauchement si longue !");
    }

    // Expression cron – tous les jours à 2h00 du matin
    @Scheduled(cron = "0 0 2 * * *")
    // @Scheduled(cron = "${cron.nettoyage}") // externalisé
    public void nettoyageQuotidien() {
        // ton code
    }

    // Démarrer 10 secondes après le démarrage, puis toutes les 30 secondes
    @Scheduled(fixedRate = 30000, initialDelay = 10000)
    public void avecDelaiInitial() { }
}


### Expressions cron les plus utiles (à garder sous la main)

| Objectif                             | Expression cron            | Signification                                   |
|--------------------------------------|----------------------------|-------------------------------------------------|
| Toutes les minutes                   | `0 * * * * *`              | À la seconde 0 de chaque minute                 |
| Toutes les 5 minutes                 | `0 */5 * * * *`            | 00:00, 00:05, 00:10…                            |
| Tous les jours à 3h30                | `0 30 3 * * *`             | Nettoyage nocturne                              |
| Tous les lundis à 9h00               | `0 0 9 ? * MON`            | Réunion hebdo                                   |
| Tous les jours ouvrés à 18h00        | `0 0 18 ? * MON-FRI`       | Fin de journée                                  |
| Dernier jour du mois à minuit        | `0 0 0 L * *`              | Clôture mensuelle                               |
| Toutes les 10 secondes               | `*/10 * * * * *`           | Pour les tests uniquement                       |

### Configuration avancée (niveau PRO)

# application-prod.yml
app:
  scheduler:
    rapport-enabled: true
    rapport-delay-ms: 120000     # 2 minutes
    nettoyage-cron: "0 0 4 * * *" # tous les jours à 4h


@Scheduled(fixedDelayString = "${app.scheduler.rapport-delay-ms}")
@ConditionalOnProperty(name = "app.scheduler.rapport-enabled", havingValue = "true")
public void rapportDynamique() { }

@Scheduled(cron = "${app.scheduler.nettoyage-cron}")
public void nettoyage() { }


### Rendre @Scheduled asynchrone (fortement recommandé !)

Par défaut, les tâches bloquent le démarrage et s’exécutent dans le même thread.


@Configuration
@EnableScheduling
@EnableAsync
public class ConfigScheduler implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        registrar.setScheduler(poolScheduler());
    }

    @Bean(destroyMethod = "shutdown")
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(15);
        scheduler.setThreadNamePrefix("sched-");
        scheduler.setErrorHandler(e -> log.error("Erreur tâche planifiée", e));
        return scheduler;
    }
}


### Arrêt propre de l’application (ne plus perdre de tâches en cours)
spring:
  task:
    scheduling:
      shutdown:
        await-termination: true
        await-termination-period: 30s


## 2. Spring AOP – Guide pratique 2025

### Activer AOP (généralement automatique dans Spring Boot)
@SpringBootApplication
// @EnableAspectJAutoProxy pas nécessaire en Spring Boot 3+
public class Application { }


### Les 5 annotations AOP à connaître absolument

| Annotation         | Quand elle s’exécute                  | Cas d’usage classique                                 |
|--------------------|---------------------------------------|-------------------------------------------------------|
| `@Before`          | Avant la méthode cible                | Log, contrôle de sécurité                             |
| `@AfterReturning`  | Après un retour réussi                | Audit, transformation de réponse                      |
| `@AfterThrowing`   | Quand une exception est levée         | Log centralisé des erreurs                            |
| `@After`           | Toujours (finally)                    | Nettoyage de ressources                               |
| `@Around`          | Autour de tout (la plus puissante)    | Métriques, cache, retry, transactions manuelles      |

### Exemple concret : Métriques + Logging + Gestion d’erreurs

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class MonitoringAspect {

    private final MeterRegistry meterRegistry;

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object monitoriserEndpoint(ProceedingJoinPoint pjp) throws Throwable {
        String nom = pjp.getSignature().toShortString();
        Timer.Sample sample = Timer.start(meterRegistry);

        try {
            Object resultat = pjp.proceed();
            sample.stop(meterRegistry.timer("http.requests", "endpoint", nom, "status", "200"));
            return resultat;
        } catch (Exception ex) {
            sample.stop(meterRegistry.timer("http.requests", "endpoint", nom, "status", "500"));
            log.error("Erreur dans {} : {}", nom, ex.toString());
            throw ex;
        }
    }
}


### Créer sa propre annotation + AOP (méthode propre)

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AvecLog {
    String valeur() default "";
}

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("@annotation(avecLog)")
    public Object loguer(ProceedingJoinPoint pjp, AvecLog avecLog) throws Throwable {
        String message = StringUtils.hasText(avecLog.valeur()) ? avecLog.valeur() : pjp.getSignature().toShortString();
        log.info("Début → {}", message);
        long debut = System.currentTimeMillis();

        try {
            Object resultat = pjp.proceed();
            log.info("Fin {} → {} ms", message, System.currentTimeMillis() - debut);
            return resultat;
        } catch (Exception e) {
            log.error("Échec {} → {} ms", message, System.currentTimeMillis() - debut, e);
            throw e;
        }
    }
}

### Pointcuts réutilisables (propreté maximale)

@Aspect
@Component
public class Pointcuts {

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void coucheService() {}

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void coucheController() {}

    @Pointcut("execution(* com.monprojet..repository.*.*(..))")
    public void coucheRepository() {}
}

// Utilisation
@Around("Pointcuts.coucheService() && @annotation(Auditable)")
public void auditer(...)

## Checklist à cocher à chaque nouveau projet

- Toujours un ThreadPoolTaskScheduler personnalisé  
- Toutes les expressions cron externalisées  
- `@Async` + `@Scheduled` combinés  
- Arrêt propre configuré  
- Jamais d’aspect lourd sur les repositories  
- `@Around` privilégié pour cache/métriques  
- Jamais d’exception avalée dans un aspect  
- Logs au bon niveau (DEBUG entrée/sortie, ERROR exception)  
- Correlation ID transmis dans les aspects si besoin  


Tu peux maintenant coller ce README directement dans ton projet. Tout est en français clair, à jour 2025 et prêt pour la production !
`
