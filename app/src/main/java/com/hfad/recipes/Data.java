package com.hfad.recipes;

public class Data {
    private static final int size = 3;
    public Recipe [] fillRecipes(){
        Recipe[] recipes_list;
        recipes_list = new Recipe[]{
                new Recipe("Watermelon Sangria", "5 minutes", R.drawable.drink1, RecipeInstruction[0]), // 1
                new Recipe("Toasted Marshmallow Campfire Cocktail", "5-6 minutes", R.drawable.drink2, RecipeInstruction[1]), //2
                new Recipe("40 Easy Summer Salads for Your Next Barbecue", "30 minutes", R.drawable.salads, RecipeInstruction[2]), //3
                new Recipe("Greek Salad Recipe", "20 minutes", R.drawable.greek_salads, RecipeInstruction[3]),//4
                new Recipe("Lentil Spaghetti Bolognese", "Less than 1 hour", R.drawable.lentil_spahetti, RecipeInstruction[4]),//5
                new Recipe("Chicken Marsala", "30 minutes", R.drawable.chicken_marsala, RecipeInstruction[5]),//6
                new Recipe("Grilled Ratatouille Linguine", "25 minutes", R.drawable.salads1, RecipeInstruction[6]),//7
                new Recipe("Glazed Bacon-Wrapped Turkey Breast", "1 - 1.5 hours", R.drawable.turkey_dinner, RecipeInstruction[7]),//8
                new Recipe("Orange-Ginger Roast Chicken With Fennel and Radicchio Salad", "1-1.25 hours", R.drawable.chicken_dinner, RecipeInstruction[8]),//9
                new Recipe("Russian wild mushroom & barley soup", "Prep: 15mins, cook: 45mins", R.drawable.russian_soup, RecipeInstruction[9]),//10
                new Recipe("Wild mushroom soup", "Prep: 15 mins, Cook: 30 mins", R.drawable.mushroom_soup, RecipeInstruction[10]),//11
                new Recipe("Mushroom soup", "Prep: 15 mins, cook: 25 mins", R.drawable.mushroom_soup1, RecipeInstruction[11]),//12
                new Recipe("Egg Sandwiches", "30 minutes", R.drawable.egg_sandwiche, RecipeInstruction[12]),//13
                new Recipe("Breakfast Burrito", "20-25 minutes", R.drawable.burittos, RecipeInstruction[13]),//14
                new Recipe("Sunny-Side Eggs", "15-17 minutes", R.drawable.sun_eggs, RecipeInstruction[14]),//15
                new Recipe("Samoa Dessert Lasagna", "35 minutes", R.drawable.samoa_desert, RecipeInstruction[15]),//16
                new Recipe("Birthday cake", "1 hour 20 minutes", R.drawable.b_cake, RecipeInstruction[16]),//17
                new Recipe("Oreo Truffles", "1 hour", R.drawable.oreo, RecipeInstruction[17])//18
        };
        return recipes_list;
    }

    public Ingredients[] fillIngredients(){
        Ingredients[] ingredients_list;
        ingredients_list = new Ingredients[]{
                new Ingredients("Apple", "5-6", 1),
                new Ingredients("Kiwi", "3.5", 2),
                new Ingredients("Milk", "1 - 1.5 liters", 3),
                new Ingredients("Green leaves", "7-10", 4),
                new Ingredients("Spaghetti", "1 kilogram", 5),
                new Ingredients("Chicken", "500 grams", 6),
                new Ingredients("Pipers", "1-2 grams", 7),
                new Ingredients("Bacon", "700-800 grams", 8),
                new Ingredients("Orange", "2-3", 9),
                new Ingredients("Wild mushrooms", "500 grams", 10),
                new Ingredients("Water", "1 - 1.5 liters", 11),
                new Ingredients("Eggs", "It's up to cooker", 12),
                new Ingredients("Burrito pad", "2-3", 13),
                new Ingredients("Salt", "1-2 grams", 14),
                new Ingredients("Oil", "15-20 milliliters", 14),
                new Ingredients("Milk", "750 milliliters", 15),
                new Ingredients("White chocolate", "250-300 grams", 16),
                new Ingredients("Oreo biscuits", "450-500 grams", 17),
                new Ingredients("Water", "900 ml", 3),
                new Ingredients("Water", "1900 ml", 5),
                new Ingredients("Water", "850 ml", 8),
                new Ingredients("Water", "740 ml", 13),
                new Ingredients("Water", "1000 ml", 15),
                new Ingredients("Oil", "2.5-3 spoons", 1),
                new Ingredients("Oil", "2-3 spoons", 17),
                new Ingredients("Oil", "3 spoons", 14),
                new Ingredients("Oil", "5 spoons", 12),
                new Ingredients("Sugar", "2.5-3 spoons", 7),
                new Ingredients("cucumber", "100-150 grams", 2),
                new Ingredients("cucumber", "100-150 grams", 7),
                new Ingredients("cucumber", "100-150 grams", 6),
                new Ingredients("cucumber", "100-150 grams", 10),
                new Ingredients("Sugar", "15 grams", 17),
                new Ingredients("Sugar", "12 grams", 16),
                new Ingredients("Sugar", "80 grams", 15),
                new Ingredients("Eggs", "It's up to cooker", 11),
                new Ingredients("Eggs", "3-5", 8),
                new Ingredients("Chicken", "300 grams", 4),
                new Ingredients("Chicken", "half kilogram", 3),
                new Ingredients("Chicken", "150-3505 grams", 9)
        };
        return ingredients_list;
    }
    public Category[] fillCategories(){
        Category[] category_list;
        category_list = new Category[]{
                new Category("Salads", 3),
                new Category("Drinks", 1),
                new Category("Drinks", 2),
                new Category("Salads", 4),
                new Category("Salads", 5),
                new Category("Salads", 6),
                new Category("Lunch", 4),
                new Category("Lunch", 5),
                new Category("Lunch", 7),
                new Category("Lunch", 8),
                new Category("Dinner", 7),
                new Category("Dinner", 9),
                new Category("Dinner", 10),
                new Category("Soup", 10),
                new Category("Soup", 11),
                new Category("Soup", 12),
                new Category("Breakfast", 13),
                new Category("Breakfast", 14),
                new Category("Breakfast", 15),
                new Category("Desert", 16),
                new Category("Desert", 17),
                new Category("Desert", 18),
                new Category("Breakfast", 18)
        };
        return category_list;
    }
    private static String[] RecipeInstruction = {
            "Place 9 cups of watermelon cubes in a blender; blend on high until very smooth. Strain juice through a mesh strainer into a large pitcher. Pour the wine, vodka, triple sec, and simple syrup into the pitcher. Stir to combine. Mix the remaining 3 cups of watermelon cubes, the lime quarters, orange sections, and blueberries into the sangria. Chill for 4 hours before serving.",
            "Place 2 to 3 heaping spoonfuls of marshmallow crème in a glass. Add vanilla and simple syrup. Pour espresso over marshmallow mixture and stir vigorously until completely combined, about 1 minute. Add vodka and ice and top with toasted marshmallows.",
            "If you're looking for some dinner salad ideas that will leave you feeling satisfied and full, look to the grilled chicken mango salad, pesto chicken Caprese salad, or the cucumber-salmon panzanella for dishes that are protein-packed. Or, if you need something light to have on the side of a bounteous summer feast, try the grilled Caesar salad or the charred corn salad. If you've been trying to add more healthy dinner ideas into your weekly meal routine, include these salads as part of your dinner repertoire for simple 30-minute meals that are good for you.",
            "Place 4 large vine tomatoes, cut into wedges, 1 peeled, deseeded and chopped cucumber, ½ a thinly sliced red onion, 16 Kalamata olives, 1 tsp dried oregano, 85g feta cheese chunks and 4 tbsp Greek extra virgin olive oil in a large bowl. Lightly season, then serve with crusty bread to mop up all of the juices.",
            "1. Cook pasta in a large saucepan of boiling salted water following packet directions. Drain and return to pan.\n" +
                    "\n" +
                    "2. Meanwhile, heat oil in a deep frying pan over medium heat. Add onion, garlic, carrot and celery. Cook, stirring occasionally, for 10 minutes or until vegetables are tender. Stir in tomato and ½ cup water and bring to the boil. Reduce heat to low and simmer for 10 minutes.",
            "Chicken Marsala is an Italian-American dish of golden pan-fried chicken cutlets and mushrooms in a rich Marsala wine sauce. Though it’s a classic restaurant dish, it’s really easy to make at home. With just one pan, you can have it on the dinner table in 45 minutes.",
            "Heat grill on medium-high. Cook linguine per package directions.\n" +
                    "Meanwhile, brush zucchini, eggplant, peppers, and red onion with oil and season with 1/2 teaspoon each salt and pepper.",

            "Preheat oven to 375 degrees F. Place turkey breast, smooth side down, on cutting board. On left breast, cut along right side of tenderloin to separate from breast without cutting tenderloin off; fold tenderloin back. Repeat on right breast, cutting along left side of tenderloin and folding back. Cover surface of turkey with 3 large sheets plastic wrap. With flat side of meat mallet or heavy rolling pin, pound turkey until about 1 inch thick all over. Discard plastic wrap.",
            "Heat oven to 350°F. Line rimmed baking sheet with parchment paper. On prepared sheet, toss fennel with oil and 1/2 teaspoon each salt and pepper. Move to outer edges of pan.\n" +
                    "Grate zest of orange into small bowl, then squeeze in 3 tablespoons juice (reserve orange halves). Whisk in honey to dissolve, then stir in ginger and fennel seeds.",
            "Cover the porcini mushrooms with 750ml boiling water and leave to soak.\n" +
                    "\n" +
                    "Heat the olive oil and butter in a large saucepan and add the chopped mushrooms, carrot and celery. Fry over a medium-high heat for around 10 mins or until the carrots are beginning to turn dark gold. Add the barley and stir the mixture for about 2 mins, then pour in the stock.",
            "Clean the mushrooms by wiping them with dry kitchen paper. Don't wash them! Separate the stems, trim off any bad parts, and coarsely chop the stems. Slice the mushroom caps 1/2cm thick and, if they are big, cut them into bite-sized pieces. Set aside.",
            "Peel and finely chop the eggs. Mix with the mayonnaise and some seasoning. Spread the bread with butter and make the sandwiches. Cut into neat fingers and stack on a plate. Scatter with cress so that those who like it can add it.",
            "Build the burrito by layering the avocado, egg mixture, about a tablespoon of pico de gallo and a sprinkle of cheese. Fold in the two sides and roll up tightly. Garnish with the reserved coriander leaves and serve with additional pico de gallo and hot sauce on the side.",
            "Heat oil in an 8-inch nonstick skillet over medium-low. Gently crack eggs into pan. You shouldn't hear a hiss, and the eggs should lie flat and still. If you hear sizzling or the whites flutter or bubble at all, turn down the heat. Cook 3 minutes or until the whites are mostly set, with some still-runny whites near the yolks. Tilt pan toward you so oil pools on the bottom edge; dip a spoon in the oil, and gently baste the uncooked patches of white until they're set. Be careful not to baste the yolks, or they'll cloud over like cataracts. Sprinkle with pepper and salt. Remove eggs from pan, leaving excess oil behind.",
            "Preheat oven to 350°. In a medium bowl, combine crushed Nillas, coconut, and melted butter. Press into the bottom of a 9”-x-13” baking dish and bake until golden, 10 to 12 minutes. Let cool. \n" +
                    "Pour caramel over cooled crust and spread into an even layer.",
            "Preheat the oven to 180C/160 fan/ gas 4 and grease and line 4 x 20cm cake tins. If you don’t have 4, halve the sponge recipe and bake in 2 batches.\n" +
                    "\n" +
                    "Put the sugar, butter and vanilla paste in a large bowl and beat with an electric whisk (or in a table top mixer) until pale and fluffy. Add the eggs gradually, beating between each addition until fully incorporated. Add a tbsp or 2 of flour if the mixture looks like it’s curdling.\n" +
                    "\n" +
                    "Sift in the flour and baking powder with a tsp of salt and fold into the cake batter. Pour in the milk and beat to loosen the mixture. Scatter over the sprinkles and ripple through the cake batter before dividing between each cake tin. Bake for 25-30 mins until golden and the sponge springs back when you press it lightly. Swap the tins around in the oven after 15 mins to ensure they cook evenly. Cool on wire racks completely before icing.\n" +
                    "\n" +
                    "Meanwhile, make the icing by beating the butter with half of the icing sugar until combined and fluffy. Add the cream cheese and the rest of the icing sugar, beating again until fully combined. Drizzle over the vanilla paste and beat until incorporated. Put a large round nozzle into a piping bag and spoon in the icing.\n" +
                    "\n" +
                    "When the cakes have cooled put a blob of icing onto a cake board and sit 1 of the sponges on top. Pipe blobs of vanilla icing in a circle covering the whole base of the sponge, then top with the next sponge. Repeat with all 3 layers, scattering over some extra sprinkles on the top. Serve in slices.",
            "Use a food processor to crush cookies into fine crumbs.\n" +
                    "Add all but 2 tablespoons crushed cookies to a medium bowl. Add cream cheese and vanilla and stir until evenly combined.\n" +
                    "Line a baking sheet with parchment paper. Using a small cookie scoop, form mixture into small balls. Place on prepared baking sheet and freeze until slightly hardened, about 30 minutes. \n" +
                    "Dip the frozen balls in melted white chocolate until coated and return to baking sheet. Drizzle with semisweet chocolate. Freeze until chocolate hardens, about 15 minutes.",
            "Just cook it by yourself :D"
    };
}
