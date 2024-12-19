import { Ingredient } from "./Ingredient";
import { Rating } from "./Rating";
import { User } from "./User";


export declare type Recipe = {
    id: number,
    name: string,
    subtitle: string,
    text: string,
    user: User,
    imageBase64: string | null,
    tags: string[],
    ingredients: Ingredient[],
    ratings: Rating[],
};
