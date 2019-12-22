package fr.xebia.jqwik.exercise8;

// TODO: move this logic to Claim, so that tests have to duplicate the complex logic
@FunctionalInterface
interface MapService {
    Distance resolveDistanceBetween(Coordinates location1, Coordinates location2);
}
