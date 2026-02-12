package com.example.taskmanager.controller;

import com.example.taskmanager.config.JwtUtil;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final JwtUtil jwtUtil;

    public TaskController(TaskService taskService, JwtUtil jwtUtil) {
        this.taskService = taskService;
        this.jwtUtil = jwtUtil;
    }

    private boolean isAdmin(String token) {
        return jwtUtil.getRole(token).equals("ADMIN");
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task,
                                        @RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", "");

        if (!isAdmin(token)) {
            return ResponseEntity.status(403).body("Forbidden: Admin only");
        }

        Task saved = taskService.createTask(task);
        return ResponseEntity.ok(saved);
    }


    @GetMapping
    public List<Task> getTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id,
                                        @RequestHeader("Authorization") String authHeader) {
        // Remove "Bearer " prefix
        String token = authHeader.replace("Bearer ", "");

        // Check role
        if (!isAdmin(token)) {
            return ResponseEntity.status(403).body("Forbidden: Admin only");
        }

        // Delete task
        taskService.deleteTask(id);

        // Return success message
        Map<String, String> response = new HashMap<>();
        response.put("message", "Task deleted successfully");
        return ResponseEntity.ok(response);
    }



    @PutMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<?> assignTask(@PathVariable Long taskId,
                                        @PathVariable Long userId,
                                        @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");

        // Only admin can assign
        if (!isAdmin(token)) {
            return ResponseEntity.status(403).body("Forbidden: Admin only");
        }

        Task updatedTask = taskService.assignTask(taskId, userId);
        return ResponseEntity.ok(updatedTask);
    }

}
