package com.Sumi.taskmate.controller;

import com.Sumi.taskmate.Repo.TaskRepo;
import com.Sumi.taskmate.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    private final TaskRepo taskRepo;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", taskRepo.findAll());
        model.addAttribute("task", new Task());
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        taskRepo.save(task);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Integer id, Model model) {
        Task task = taskRepo.findById(id).orElse(null);
        model.addAttribute("task", task);
        model.addAttribute("tasks", taskRepo.findAll());
        return "index";
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        taskRepo.save(task);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        taskRepo.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/toggle/{id}")
    public String toggleTask(@PathVariable Integer id) {
        Task task = taskRepo.findById(id).orElse(null);
        if (task != null) {
            task.setCompleted(!task.isCompleted());
            taskRepo.save(task);
        }
        return "redirect:/";
    }

}