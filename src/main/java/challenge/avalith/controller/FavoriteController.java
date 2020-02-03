package challenge.avalith.controller;

import challenge.avalith.model.Favorite;
import challenge.avalith.service.FavoriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/launches/favorites")
@Api(value="Favorite Launches Controller", description = "Endpoints to manage favorite launches")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @ApiOperation(value = "Get all favorite launches")
    @RequestMapping(method = RequestMethod.GET)
    public  ResponseEntity<List<Favorite>> getFavorites() {
        List<Favorite> favorites =  favoriteService.getAll();
        return  ResponseEntity.ok(favorites);
    }

    @ApiOperation(value = "Delete a favorite launch")
    @RequestMapping( value = "/{favoriteId}", method = RequestMethod.DELETE)
    public  ResponseEntity<Void> deleteFavorite(@PathVariable Long favoriteId) {
        favoriteService.delete(favoriteId);
        return  ResponseEntity.noContent().build();
    }
}
