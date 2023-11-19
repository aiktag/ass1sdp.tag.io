class ArmorDecorator extends HeroDecorator {
    public ArmorDecorator(Hero decoratedHero) {
        super(decoratedHero);
    }

    @Override
    public void equip() {
        super.equip();
        System.out.println("Equipping Armor");
    }
}