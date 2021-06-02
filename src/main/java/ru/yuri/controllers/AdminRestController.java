package ru.yuri.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yuri.model.People;
import ru.yuri.model.Role;
import ru.yuri.services.PeopleService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admins")
public class AdminRestController {

    private final PeopleService service;

    public AdminRestController(PeopleService service) {
        this.service = service;
    }
    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody People user) {
        service.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<People>> readAll() {
        List<People> list = service.index();
        return list != null && !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<People> read(@PathVariable int id) {
        People user = service.get(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/one/{name}")
    public ResponseEntity<People>get(@PathVariable String name){
        People user = service.getOneUserByName(name);
        return user !=null ? new ResponseEntity<>(user, HttpStatus.OK):new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/allRoles")
    public ResponseEntity<List<Role>> getAllRoles(){
        List<Role> list = service.getAllRoles();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<People> update(@RequestBody People user) {
        boolean res = service.update(user);
        return res
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<People> delete(@PathVariable int id) {
        boolean bool = service.delete(id);
        return bool
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
