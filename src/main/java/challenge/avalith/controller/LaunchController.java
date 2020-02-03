package challenge.avalith.controller;

import challenge.avalith.model.Launch;
import challenge.avalith.model.Tag;
import challenge.avalith.service.LaunchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/launches")
@Api(value="Launches Controller", description = "Endpoints to manage launches")
public class LaunchController {

    @Autowired
    private LaunchService launchService;

    @ApiOperation(value = "Get all upcoming launches from spaceX api")
    @RequestMapping(value = "/upcoming", method = RequestMethod.GET)
    public ResponseEntity<Launch[]> getUpcoming() {
        return ResponseEntity.ok(launchService.getUpcomingLaunches());
    }

    @ApiOperation(value = "Add a launch to favorite list")
    @RequestMapping( value = "/{launchNumber}/favorite", method = RequestMethod.POST)
    public  ResponseEntity<Launch> addToFavorites(@PathVariable Integer launchNumber, @RequestBody List<Tag> tags) {
         Launch launch =  launchService.addToFavorites(launchNumber, tags);
        return  ResponseEntity.ok(launch);
    }
}
