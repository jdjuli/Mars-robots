package app.contoller;

import app.model.ProblemBuilder;
import app.model.ProblemInstance;
import app.model.ProblemStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import java.io.Serializable;

@RestController
public class ProblemInstanceController {

    private static class Input{
        private String text;
        public Input(String text) {
            this.text = text;
        }
        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
    }

    private static class Response implements Serializable {
        private boolean success;
        private ProblemStats stats;
        private String output;
        public Response(){}
        public Response(boolean success, ProblemStats stats, String output) {
            this.success = success;
            this.stats = stats;
            this.output = output;
        }
        public boolean isSuccess() {
            return success;
        }
        public void setSuccess(boolean success) {
            this.success = success;
        }
        public ProblemStats getStats() {
            return stats;
        }
        public void setStats(ProblemStats stats) {
            this.stats = stats;
        }
        public String getOutput() {
            return output;
        }
        public void setOutput(String output) {
            this.output = output;
        }
    }

    @Autowired
    ProblemBuilder problemBuilder;

    @PostMapping("/solve")
    public Response processForm(Input input){
        try{
            ProblemInstance problemInstance = problemBuilder.fromString(input.getText());
            ProblemStats stats = problemInstance.solve();
            return new Response(true,stats,stats.generateOutput());
        }catch (RuntimeException re){
            return new Response(false, null, "Error: "+re.getMessage());
        }
    }
}
