package pe.fact.gestor.comercial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.fact.gestor.comercial.entity.Plan;
import pe.fact.gestor.comercial.service.PlanService;

@RestController
@RequestMapping("/api/planes")
public class PlanController {

    @Autowired
    private PlanService service;

    //POST (api/planes/guardar)
    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody Plan plan) {
        try {
            service.crear(plan);
            return ResponseEntity.ok("Plan registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
    //GET (api/planes/listar)
    @GetMapping("/listar")
    public ResponseEntity<java.util.List<Plan>> listar() {
        try {
            return ResponseEntity.ok(service.listarTodos());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}