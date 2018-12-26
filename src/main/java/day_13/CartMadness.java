package day_13;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CartMadness {

    final private char[][] baseMap;
    final List<MineCart> carts;
    final Map<MineCart, Integer> cartDirections = new HashMap<>();
    final Map<Character, Character> turnLeft = new HashMap<>();
    final Map<Character, Character> turnRight = new HashMap<>();

    public CartMadness(final List<String> input) {
        final int height = input.size();
        final int width = input.stream().map(s -> s.length()).max(Comparator.comparingInt(i -> i)).get();

        baseMap = new char[height][width];

        for (int i = 0; i < height; i++) {
            baseMap[i] = input.get(i).toCharArray();
        }

        carts = removeCartsFromBaseMap();

        for (final MineCart cart : carts) {
            cartDirections.put(cart, 0);
        }

        initialiseDirectionMaps();

    }

    protected void initialiseDirectionMaps() {
        turnLeft.put('>', '^');
        turnLeft.put('^', '<');
        turnLeft.put('<', 'v');
        turnLeft.put('v', '>');

        turnRight.put('>', 'v');
        turnRight.put('v', '<');
        turnRight.put('<', '^');
        turnRight.put('^', '>');
    }


    protected List<MineCart> removeCartsFromBaseMap() {
        final List<MineCart> carts = new ArrayList<>();
        for (int i = 0; i < baseMap.length; i++) {
            for (int j = 0; j < baseMap[i].length; j++) {
                final char c = baseMap[i][j];
                if ("^<>v".indexOf(c) != -1) {
                    final MineCart cart = new MineCart();
                    cart.setX(j);
                    cart.setY(i);
                    cart.setDirection(c);
                    carts.add(cart);

                    // Remove the cart from the base map
                    if ("^v".indexOf(c) != -1) {
                        baseMap[i][j] = '|';
                    } else {
                        baseMap[i][j] = '-';
                    }
                }
            }
        }
        return carts;
    }

    public MineCart identifyFirstCollision() {
        while (true) {
            carts.sort(Comparator.comparingInt(MineCart::getY).thenComparing(MineCart::getX));
            final char[][] currentMap = copyArray(baseMap);
            for (final MineCart cart : carts) {
                move(cart);
                if (hasCollided(cart) != null) {
                    return cart;
                }
                currentMap[cart.getY()][cart.getX()] = cart.getDirection();
            }
        }
    }

    public MineCart identifyLastCart() {
        while (carts.size() > 1) {
            carts.sort(Comparator.comparingInt(MineCart::getY).thenComparing(MineCart::getX));
            final List<MineCart> collisionList = new ArrayList<>();

            final char[][] currentMap = copyArray(baseMap);
            for (final MineCart cart : carts) {
                move(cart);
                MineCart collisionCart;
                if ((collisionCart = hasCollided(cart)) != null) {
                    collisionList.add(cart);
                    collisionList.add(collisionCart);
                    currentMap[cart.getY()][cart.getX()] = baseMap[cart.getY()][cart.getX()];
                } else {
                    currentMap[cart.getY()][cart.getX()] = cart.getDirection();
                }
            }

            if (collisionList.size() > 0) {
                carts.removeAll(collisionList);
            }
        }

        return carts.get(0);
    }


    protected void move(final MineCart cart) {
        final char c = cart.getDirection();
        char nextTrack;
        switch (c) {
            case '>':
                nextTrack = baseMap[cart.getY()][cart.getX() + 1];
                cart.setDirection(nextDirection(nextTrack, cart));
                cart.setX(cart.getX() + 1);
                break;
            case '<':
                nextTrack = baseMap[cart.getY()][cart.getX() - 1];
                cart.setDirection(nextDirection(nextTrack, cart));
                cart.setX(cart.getX() - 1);
                break;
            case '^':
                nextTrack = baseMap[cart.getY() - 1][cart.getX()];
                cart.setDirection(nextDirection(nextTrack, cart));
                cart.setY(cart.getY() - 1);
                break;
            case 'v':
                nextTrack = baseMap[cart.getY() + 1][cart.getX()];
                cart.setDirection(nextDirection(nextTrack, cart));
                cart.setY(cart.getY() + 1);
                break;
            default:
                // Shouldn't ever be reached
        }
    }

    protected char nextDirection(final char track, final MineCart cart) {
        final char direction = cart.getDirection();
        char nextDirection = direction;
        if (isHorizontal(direction)) {
            if (track == '\\') {
                nextDirection = turnRight.get(direction);
            } else if (track == '/') {
                nextDirection = turnLeft.get(direction);
            }
        } else if (isVertical(direction)) {
            if (track == '\\') {
                nextDirection = turnLeft.get(direction);
            } else if (track == '/') {
                nextDirection = turnRight.get(direction);
            }
        }

        if (track == '+') {
            final int i = cartDirections.get(cart) % 3;
            switch (i) {
                case 0:
                    nextDirection = turnLeft.get(direction);
                    cartDirections.put(cart, cartDirections.get(cart) + 1);
                    break;
                case 1: {
                    nextDirection = direction;
                    cartDirections.put(cart, cartDirections.get(cart) + 1);
                    break;
                }
                case 2:
                    nextDirection = turnRight.get(direction);
                    cartDirections.put(cart, cartDirections.get(cart) + 1);
                    break;
                default:
                    // Shouldn't ever be reached
            }
        }
        return nextDirection;
    }

    private boolean isHorizontal(final char direction) {
        return "<>".indexOf(direction) != -1;
    }

    private boolean isVertical(final char direction) {
        return "^v".indexOf(direction) != -1;
    }

    private MineCart hasCollided(final MineCart cart) {
        for (final MineCart c : carts) {
            if (c.locationEquals(cart) && !c.equals(cart)) {
                return c;
            }
        }
        return null;
    }

    private char[][] copyArray(final char[][] toCopy) {
        final char[][] copy = new char[toCopy.length][];
        for (int i = 0; i < toCopy.length; i++) {
            copy[i] = Arrays.copyOf(toCopy[i], toCopy[i].length);
        }
        return copy;
    }

    public List<MineCart> getCarts() {
        return this.carts;
    }

    public static void main(String[] args) throws IOException {
        final List<String> lines = FileUtils.readLines(new File("src/main/resources/13_input.txt"), "UTF-8");
        final CartMadness partOne = new CartMadness(lines);
        final MineCart cart = partOne.identifyFirstCollision();
        System.out.println(cart.getX() + "," + cart.getY());
        final CartMadness partTwo = new CartMadness(lines);
        final MineCart cartTwo = partTwo.identifyLastCart();
        System.out.println(cartTwo.getX() + "," + cartTwo.getY());
    }
}
